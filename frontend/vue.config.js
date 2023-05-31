const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
    transpileDependencies: ["vuetify"],
    outputDir: "../server/src/main/resources/static",
});
