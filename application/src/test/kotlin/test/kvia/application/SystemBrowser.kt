package test.kvia.application

import io.github.nishikigii.kvia.basic.application.Browser
import java.net.URI

/**
 *
 */
private fun main()
{
    Browser.systemDefault.unwrap().open( URI("") )
    Browser.open("https://www.bing.com/")
}
