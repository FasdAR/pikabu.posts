package ru.fasdev.pikabuposts.domain.post.model

data class Post(val id: Long, val title: String?, val body: String?, val images: List<String>?)
{
    override fun hashCode(): Int {
        return id.toInt() + title.toString().length + body.toString().length + (images?.size ?: 0)
    }

    override fun equals(other: Any?): Boolean {
        return (other as Post).hashCode() == hashCode()
    }
}