package chefchallenge.frontend.mobile.domain.repository

interface ReceipeRepository {
    fun getReceipeByName();
    fun getAllReceipes();
    fun createReceipe();
    fun updateReceipe();
    fun deleteReceipe();
}