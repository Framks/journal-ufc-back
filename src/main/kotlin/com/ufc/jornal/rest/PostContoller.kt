package com.ufc.jornal.rest

import com.ufc.jornal.constants.URL.POST
import com.ufc.jornal.rest.request.post.PostPosResquest
import com.ufc.jornal.usecases.post.CreatePost
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(POST)
class PostContoller(
    private val createPost: CreatePost,
) {

    @PostMapping
    fun createPost(
        @RequestBody
        request: PostPosResquest
    ) {
        // implementar a logica de criacao de postagem
    }

    // todo fazer a listagem das postagens de forma paginada por data mais recente, com filtro por autor e por palavra chave no titulo ou conteudo

    // todo fazer a edicao da postagem

    // Todo fazer a exclusão logica da postagem

    // todo fazer o comentario de uma postagem

    // todo fazer o like das postagens
}