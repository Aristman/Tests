package com.geekbrains.tests

internal const val TEST_NUMBER = 42
internal const val RESPONSE_NULL = "Response is null or unsuccessful"
internal const val SEARCH_RESULT_NULL = "Search results or total count are null"
internal const val TEXT_SOME_QUERY = "some query"
internal const val TOTAL_COUNT_EXTRA = "TOTAL_COUNT_EXTRA"

internal fun getTextNumberOfResult(count: Int): String =
    String.format("Number of results: %d", count)
