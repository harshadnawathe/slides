package com.github.harshadnawathe.model

import reactor.core.publisher.Mono

//START OMIT
interface ComposableModel<T : Any> {
    val value: T              // Will throw error if value is not present
    val isComplete: Boolean   // Predicate to check if value is present
    infix fun with(data: Any): ComposableModel<T>
    infix fun with(data: Mono<*>): Mono<out ComposableModel<T>>
}

infix fun <T : Any> Mono<out ComposableModel<T>>.with(data: Any): Mono<ComposableModel<T>> =
    map { it with data }

infix fun <T : Any> Mono<out ComposableModel<T>>.with(data: Mono<*>): Mono<ComposableModel<T>> =
    flatMap { it with data }
//END OMIT