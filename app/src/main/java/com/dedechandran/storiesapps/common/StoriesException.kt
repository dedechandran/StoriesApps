package com.dedechandran.storiesapps.common

sealed class StoriesException: Throwable() {
    object UserNotFoundException: StoriesException()
    object UserUnauthorizedException: StoriesException()
    object UnknownErrorException: StoriesException()
    object BadRequestException: StoriesException()
    object NoNetworkException: StoriesException()
}
