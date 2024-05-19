package io.github.nishikigii.kvia.basic

import io.github.nishikigii.kvia.basic.SystemType.Android
import io.github.nishikigii.kvia.basic.SystemType.IOS
import io.github.nishikigii.kvia.basic.SystemType.Linux
import io.github.nishikigii.kvia.basic.SystemType.MacOS
import io.github.nishikigii.kvia.basic.SystemType.Others
import io.github.nishikigii.kvia.basic.SystemType.Windows

private val os = System.getProperty("os.name").lowercase()


/**
 * operating system type
 */
enum class SystemType
{
    // https://www.apple.com/macos
    MacOS,

    // https://www.microsoft.com/windows/
    Windows,

    // https://wikipedia.org/wiki/Linux
    Linux,

    // https://www.android.com/
    Android,

    // https://www.apple.com/ios/
    IOS,

    Others ;

    /**
     * if this enum matches anyone inside the provided list, execute provided action
     *
     * @param list matching list
     * @param action the task that will be executed if the match is successful
     */
    fun matches( vararg list: SystemType, action: ()->Unit ) = if ( this !in list ) false else { action(); true }


    /**
     * if this enum same to the type of current system, execute provided action
     *
     * @param action the action that will be executed if this enum is same to the type of current system
     */
    fun isCurrent( action: (SystemType)->Unit ) = if ( systemType != this ) false else { action(this); true }

}

/**
 * the type of current system
 */
val systemType = when {
    os.contains("mac") -> MacOS
    os.contains("win") -> Windows
    os.contains("nix") || os.contains("nux") || os.contains("aix") -> Linux
    os.contains("android") -> Android
    os.contains("ios") -> IOS
    else -> Others
}
