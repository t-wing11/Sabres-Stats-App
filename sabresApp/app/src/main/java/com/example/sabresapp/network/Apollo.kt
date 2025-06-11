import com.apollographql.apollo.ApolloClient

object ApolloInstance {
    val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("http://192.168.50.173:4000/graphql")
        .build()
}


