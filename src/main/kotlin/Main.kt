import services.GithubLoader

fun main(args:Array<String>) {
    val loader = GithubLoader()
    print(loader.getAuthenticatedUser())
}