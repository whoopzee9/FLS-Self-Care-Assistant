package selfcareassistant.entity

object Constants {
    const val JWT_SECRET = "eyJhbGciOiJIUzUxMiJ9.ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoI" +
            "iwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn0.BrDQYqyG8W9Cvi-_zQNeJN2aLUea7YVNvbHu5NPeWZE-SzkkgGTPxukUGHexHE6-FT2Wk9" +
            "S_fP0Hgh8Jy7xBYQ"
    const val JWT_EXPIRATION = 24 * 60 * 1000

    const val TOKEN_HEADER = "Authorization"
}