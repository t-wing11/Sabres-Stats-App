import com.apollographql.apollo.ApolloClient

object ApolloInstance {
    val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("http://192.168.2.196:4000/graphql")
        .build()
}


