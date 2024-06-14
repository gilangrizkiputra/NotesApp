package com.sukasrana.notesapp.data.local.authLogin

sealed  class Resource<out R> {
    data class Success<out R>(val result: R): Resource<R>()
    data class Failure(val exception: Exception): Resource<Nothing>()


}