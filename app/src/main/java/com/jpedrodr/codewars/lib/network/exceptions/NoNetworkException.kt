package com.jpedrodr.codewars.lib.network.exceptions

import java.io.IOException

class NoNetworkException(override val message: String?) :
    IOException() // IOException otherwise it isn't propagated to the caller