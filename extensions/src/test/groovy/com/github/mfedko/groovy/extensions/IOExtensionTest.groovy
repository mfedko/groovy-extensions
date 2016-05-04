package com.github.mfedko.groovy.extensions

import spock.lang.Specification


class IOExtensionTest extends Specification {

    def "WithGzipReader"() {

        expect:
            inTxt == inGzContent

        where:
            inTxt = getClass().getResource("/in.txt").text.replaceAll('\r', '')
            inGzContent = getClass().getResource("/in.txt.gz").withGzipReader { it.text.replaceAll('\r', '') }
    }

    def "WithGzipWriter"() {

        given:
            def inTxt = getClass().getResource("/in.txt").text.replaceAll('\r', '')
            def file = File.createTempFile("IOExtensionTest_", ".gz")

        when:
            file.deleteOnExit()
            file.withGzipWriter { it << inTxt }
            def output = file.withGzipReader { it.text.replaceAll('\r', '') }

        then:
            inTxt == output
    }
}
