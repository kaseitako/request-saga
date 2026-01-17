# request-saga

## 開発環境

- **Java**: 25
- **ビルドツール**: Gradle (Kotlin DSL)
- **テストフレームワーク**: JUnit 5

## セットアップ

### IntelliJ IDEA

プロジェクトをそのまま開いてください。Gradleプロジェクトとして自動認識されます。

### VSCode (devcontainer)

1. [Dev Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)拡張機能をインストール
2. プロジェクトを開き、「Reopen in Container」を選択

devcontainerは自動的にJava環境とGradleをセットアップします。

## ビルドとテスト

```bash
# ビルド
./gradlew build

# テスト実行
./gradlew test
```

ビルド時に自動的にSpotlessによるコードフォーマットが適用されます。
