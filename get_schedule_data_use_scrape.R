library(rvest)
library(polite)
library(glue)
library(dplyr)
library(readr)
library(here)

# change this to be the path to where you want to save the files
base_path <- "~/Documents/CSCI201L"
data_folder <-  "schedule_data"
data_path <- glue("{base_path}/{data_folder}")

# Make a folder to store all of the csvs we have to download if that folder DNE
if(!dir.exists(data_path)){
    dir.create(data_path)
}

# create session for scraping
session <- bow("https://classes.usc.edu", force=T)

# Code to get prefix codes from the website
page <- read_html("https://classes.usc.edu/term-20203/")

prefix_codes = page %>% 
    html_nodes(".prefix") %>% 
    html_text() %>% 
    tolower()

# exclude the codes for which we already have downloaded the schedule
already_downloaded <- list.files(data_path, pattern = "*.csv") %>% 
    stringr::str_remove(.,".csv")

remaining_codes <- setdiff(prefix_codes,already_downloaded)

    
# using the polite library to get the data without spamming their site and potentially getting blocked
# downside is that it will take longer because of the website's robots.txt
# for (code in remaining_codes) {
#     print(code)
#     nod(bow = session,
#         path = paste0("/term-20193/csv/", code, ".csv")) %>%
#         rip(path = here("schedule_data"), overwrite = T)
# }


#### SCRAPE COMMAND VERSION ######

# use scrape instead of rip to avoid downloading the file
schedule_data <- lapply(remaining_codes, function(code){
    print(code)
    nod(bow = session,
        path = paste0("/term-20203/csv/", code, ".csv")) %>%
        scrape(content="text; charset=UTF-8") %>% read_csv(.,col_types = cols(
            `Course number` = col_character(),
            `Course title` = col_character(),
            `Registration restrictions` = col_character(),
            Units = col_character(),
            Type = col_character(),
            Section = col_character(),
            Session = col_character(),
            Time = col_character(),
            Days = col_character(),
            Seats = col_double(),
            Registered = col_double(),  
            Waitlist = col_double(),
            Instructor = col_character(),
            Room = col_character()))
})

View(schedule_data)
df1 <- do.call(rbind.data.frame, schedule_data)
df2 <- na.omit(df1)
write_csv(df2, path = glue("~/Documents/CSCI201L/201FinalProject/fall2020schedule.csv"))

?do.call
#### RIP COMMAND VERSION ######

# take all of the downloaded files and bind them into one data frame. This could probably be done the loop using a tmp file?
downloaded_files <- list.files(here("schedule_data"), pattern = "*.csv")

all_schedule_data <- downloaded_files %>% paste0("Documents/CSCI201L/schedule_data/",.) %>%  here() %>% 
    lapply(read_csv, col_types = cols(
        `Course number` = col_character(),
        `Course title` = col_character(),
        `Registration restrictions` = col_character(),
        Units = col_character(),
        Type = col_character(),
        Section = col_character(),
        Session = col_character(),
        Time = col_character(),
        Days = col_character(),
        Seats = col_double(),
        Registered = col_double(),
        Waitlist = col_double(),
        Instructor = col_character(),
        Room = col_character())) %>% 
    bind_rows 

all_schedule_data <- all_schedule_data %>% tidyr::fill(`Course number`,`Course title`)

write_csv(all_schedule_data,path = glue("{data_path}/fall2020schedule.csv"))
