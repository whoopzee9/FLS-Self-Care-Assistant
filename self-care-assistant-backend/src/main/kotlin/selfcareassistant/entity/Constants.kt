package selfcareassistant.entity

object Constants {
    const val JWT_SECRET = "eyJhbGciOiJIUzUxMiJ9ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoI" +
            "iwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn0BrDQYqyG8W9CvizQNeJN2aLUea7YVNvbHu5NPeWZESzkkgGTPxukUGHexHE6FT2Wk9" +
            "SfP0Hgh8Jy7xBYQ"
    const val JWT_EXPIRATION = 24 * 60 * 60 * 1000

    const val TOKEN_HEADER = "Authorization"
}