package com.github.mfedko.groovy.extensions

import groovy.transform.CompileStatic
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import org.codehaus.groovy.runtime.IOGroovyMethods

import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

@CompileStatic
class IOExtension {

    static <T> T withGzipReader(File file, @ClosureParams(value=SimpleType.class, options="java.io.Reader") Closure<T> closure) {
        return withGzipReader(new FileInputStream(file), closure)
    }

    static <T> T withGzipReader(URL url, @ClosureParams(value=SimpleType.class, options="java.io.Reader") Closure<T> closure) {
        return withGzipReader(url.openStream(), closure)
    }

    static <T> T withGzipReader(InputStream is, @ClosureParams(value=SimpleType.class, options="java.io.Reader") Closure<T> closure) {
        return IOGroovyMethods.withReader(new GZIPInputStream(is), closure)
    }

    static <T> T withGzipWriter(File file, @ClosureParams(value=SimpleType.class, options="java.io.Writer") Closure<T> closure) {
        return withGzipWriter(new FileOutputStream(file), closure)
    }

    static <T> T withGzipWriter(OutputStream os, @ClosureParams(value=SimpleType.class, options="java.io.Writer") Closure<T> closure) {
        return IOGroovyMethods.withWriter(new GZIPOutputStream(os), closure)
    }

    static <T> T withCloseable(AutoCloseable ac, @ClosureParams(value=SimpleType.class, options="java.lang.AutoCloseable") Closure<T> closure) {
        try {
            T result = closure.call()
            return result
        } finally {
            ac.close()
        }
    }
}
