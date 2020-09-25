package ru.fasdev.pikabuposts.domain.post.model

data class Post(val id: Long, val title: String?, val body: String?, val images: List<String>?, var isSaved: Boolean = false)