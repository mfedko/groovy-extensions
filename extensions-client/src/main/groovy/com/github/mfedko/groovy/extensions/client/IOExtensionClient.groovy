package com.github.mfedko.groovy.extensions.client

import groovy.transform.CompileStatic

@CompileStatic
class IOExtensionClient {

    public static void main(String[] args) {

        def url = IOExtensionClient.class.getResource("/extensions_client.txt")
        url.withGzipReader { Reader r -> }
    }
}
