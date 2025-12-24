/*
 * @Author: huah 751317151@qq.com
 * @Date: 2025-12-06 02:21:01
 * @LastEditors: huah 751317151@qq.com
 * @LastEditTime: 2025-12-06 15:33:51
 * @FilePath: \spring-ai-protal\vite.config.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { fileURLToPath, URL } from "node:url";

import { defineConfig, loadEnv } from "vite";
import vue from "@vitejs/plugin-vue";
import vueDevTools from "vite-plugin-vue-devtools";

// https://vite.dev/config/
export default defineConfig(({ mode }) => {
  // 加载环境变量
  const env = loadEnv(mode, process.cwd(), "");

  return {
    // plugins: [vue(), vueDevTools()], // 添加 vue-devtools 插件
    plugins: [vue()],
    resolve: {
      alias: {
        "@": fileURLToPath(new URL("./src", import.meta.url)),
      },
    },
    server: {
      host: "127.0.0.1", // 使用 IPv4 避免 IPv6 权限问题
      port: Number(env.VITE_PORT) || 8080, // 从环境变量读取端口，默认8080
      strictPort: false, // 如果端口被占用，自动尝试下一个可用端口
      headers: {
        "Cross-Origin-Opener-Policy": "same-origin",
        "Cross-Origin-Embedder-Policy": "require-corp",
      },
    },
    optimizeDeps: {
      exclude: ["@pdftron/webviewer"],
    },
  };
});
