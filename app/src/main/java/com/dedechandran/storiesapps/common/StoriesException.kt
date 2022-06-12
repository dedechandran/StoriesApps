package com.dedechandran.storiesapps.common

sealed class StoriesException(override val message: String?): Throwable(message) {
    object UserNotFoundException: StoriesException("User Not Found")
    object UserUnauthorizedException: StoriesException("User Unauthorized")
    object UnknownErrorException: StoriesException("Unknown Error")
    object BadRequestException: StoriesException("Bad Request Error")
    object NoNetworkException: StoriesException("No Internet Connection")
}
