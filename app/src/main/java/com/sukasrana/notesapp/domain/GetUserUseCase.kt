package com.sukasrana.notesapp.domain

import com.sukasrana.notesapp.data.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getUsers()
}