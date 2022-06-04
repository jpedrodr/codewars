package com.jpedrodr.codewars.lib

// This should be in a separate kotlin module since it has no Android dependencies.
// Lib shouldn't have access to the layers above (i.e domain and app)
interface Lib {

    companion object {
        operator fun invoke(): Lib = LibCompositionRoot()
    }

}