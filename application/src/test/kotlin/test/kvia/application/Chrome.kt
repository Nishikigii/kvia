package test.kvia.application

import io.github.nishikigii.kvia.basic.application.Browser
import io.github.nishikigii.kvia.basic.pause
import java.net.URI

/**
 *
 */
private fun main()
{
    val path = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"
    val chrome = Browser.Chrome.local(path).unwrap()

    chrome.open( URI("https://www.bing.com/") )
    pause(500)
}