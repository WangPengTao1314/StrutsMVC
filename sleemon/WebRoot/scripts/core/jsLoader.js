var JSLoader = {

    scripts: {

        jquery: "jquery-1.6.3.min.js"

    },

    browser: {

        ie: /msie/.test(window.navigator.userAgent.toLowerCase()),

        moz: /gecko/.test(window.navigator.userAgent.toLowerCase()),

        opera: /opera/.test(window.navigator.userAgent.toLowerCase()),

        safari: /safari/.test(window.navigator.userAgent.toLowerCase())

    },

    call: (function () {

        function hasFile(tag, url) {

            var contains = false;

            var files = document.getElementsByTagName(tag);

            var type = tag == "script" ? "src" : "href";

            for (var i = 0, len = files.length; i < len; i++) {

                if (files[i].getAttribute(type) == url) {

                    contains = true;

                    break;

                }

            }

            return contains;

        }

        function loadFile(element, callback, parent) {

            var p = parent && parent != undefined ? parent : "head";

            document.getElementsByTagName(p)[0].appendChild(element);

            if (callback) {

                //ie

                if (JSLoader.browser.ie) {

                    element.onreadystatechange = function () {

                        if (this.readyState == 'loaded' || this.readyState == 'complete') {

                            callback();

                        }

                    };

                } else if (JSLoader.browser.moz) {

                    element.onload = function () {

                        callback();

                    };

                } else {

                    callback();

                }

            }

        }

        function loadCssFile(files, callback) {

            var urls = files && typeof (files) == "string" ? [files] : files;

            for (var i = 0, len = urls.length; i < len; i++) {

                var cssFile = document.createElement("link");

                cssFile.setAttribute('type', 'text/css');

                cssFile.setAttribute('rel', 'stylesheet');

                cssFile.setAttribute('href', urls[i]);

                if (!hasFile("link", urls[i])) {

                    loadFile(cssFile, callback);

                }

            }

        }

        function loadScript(files, callback, parent) {

            var urls = files && typeof (files) == "string" ? [files] : files;

            for (var i = 0, len = urls.length; i < len; i++) {

                var script = document.createElement("script");

                script.setAttribute('charset', 'gb2312');

                script.setAttribute('type', 'text/javascript');

                script.setAttribute('src', urls[i]);

                if (!hasFile("script", urls[i])) {

                    loadFile(script, callback, parent);

                }

            }

        }

        function includeFile(options) {
           loadCssFile(options.cssFiles, function () {
            loadScript(JSLoader.scripts.jquery, function () {
                 loadScript(options.scripts, null, "body");

                });

            });

        }

        return { include: includeFile };

    })()

};

var include = function (options) {

    JSLoader.call.include(options);

}
