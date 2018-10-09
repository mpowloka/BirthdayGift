package com.mpowloka.birthdaygift.common.glide

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Cannot inject GlideRequests with Dagger2, because of annotation processor and kapt issues.
 * Adding this to avoid mocking static GlideApp.with() call in tests.
 * See more:
 * https://stackoverflow.com/questions/45099688/error-error-nonexistentclass-kotlin-in-multi-module-dagger-project
 */
@GlideModule
class GlideAppModule : AppGlideModule()