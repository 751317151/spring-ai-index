/*
 * @Author: huah 751317151@qq.com
 * @Date: 2025-12-06 03:13:07
 * @LastEditors: huah 751317151@qq.com
 * @LastEditTime: 2025-12-06 03:17:20
 * @FilePath: \spring-ai-protal\scripts\copy-webviewer.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import fs from "fs-extra";
import path from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const sourceDir = path.join(
  __dirname,
  "..",
  "node_modules",
  "@pdftron",
  "webviewer",
  "public"
);
const destDir = path.join(__dirname, "..", "public", "webviewer");

async function copyWebViewer() {
  try {
    // Check if source directory exists
    if (!(await fs.pathExists(sourceDir))) {
      console.warn(`Warning: Source directory does not exist: ${sourceDir}`);
      console.warn(
        "Skipping webviewer copy. This is normal if @pdftron/webviewer is not installed yet."
      );
      return;
    }

    // Ensure destination directory exists
    await fs.ensureDir(destDir);

    // Copy files
    await fs.copy(sourceDir, destDir, {
      overwrite: true,
    });

    console.log("WebViewer files copied successfully!");
  } catch (error) {
    console.error("Error copying WebViewer files:", error);
    process.exit(1);
  }
}

copyWebViewer();
