package ru.fasdev.pikabuposts.domain.post.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostTest
{
    @Test
    fun testGetHashCode() {
        val testPost = Post(10, "21", "12", null)
        assertThat(testPost.hashCode()).isEqualTo(14)
    }

    @Test
    fun testEquals() {
        val post = Post(10, "21", "31", null)

        assertThat(post.equals(Post(10, "21", "31", null))).isEqualTo(true)
    }
}