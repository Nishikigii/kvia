package io.github.nishikigii.kvia.basic

/**
 * wrappers an instance with a specify version number.
 */
class Version<Inst>( val instance: Inst, val ver: UInt )

/**
 * quick creating a package of instance with version number.
 */
@Suppress("NOTHING_TO_INLINE")
inline infix fun<Instance> Instance.ver( ver: UInt ) = Version(this, ver)