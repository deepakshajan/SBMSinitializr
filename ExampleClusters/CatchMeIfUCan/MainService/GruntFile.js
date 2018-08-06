module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        lineremover: {
            customExclude: {
                files: {
                    'src/main/resources/static/js/game.min.js': 'src/main/resources/static/js/game.js'
                },
                options: {
                    exclusionPattern: /module\.exports.=.cmiuc;/g
                }
            },
        },
        uglify: {
            build: {
                src: 'src/main/resources/static/js/game.min.js',
                dest: 'src/main/resources/static/js/game.min.js'
            }
        }
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-uglify-es');
    // Load the plugin that removes the line "module.exports = cmiuc;"
    grunt.loadNpmTasks('grunt-line-remover');

    // Default task(s).
    grunt.registerTask('default', ['lineremover','uglify']);

};