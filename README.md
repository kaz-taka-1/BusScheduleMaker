# バス時刻表ツクール

## アプリケーションの概要
表バスの時刻表を作成し、いつでも閲覧や編集ができるアプリです。
このアプリを使うことで、スクールバスや会社の通勤バスなどのいつも利用するバスの時刻表をアプリで確認できるようになります。

### 検索に引っかからないバスの時刻表をアプリで管理
学校のスクールバスや地域のローカルバスなど、経路案内の検索に引っかからないバスの時刻表をこのアプリ内に登録することで、いつでも時刻表を閲覧できるようにする。

### いつでも自分で時刻表を編集できる
時刻表が変更になったり、利用しなくなったバスがある場合は自分でいつでも時刻表を変更、消去ができる。

## Google playのURL
https://play.google.com/store/apps/details?id=com.websarva.wings.android.busschedulemaker

## 利用方法
### 時刻表の作成
1.トップページのメニューから新規時刻表を作成を選択する。

2.タイトル、行き先、乗車場所、運行時間を入力し、時刻表入力欄作成ボタンをタップする。

3.表示された時刻表にバスの出発時刻を入力し、保存ボタンをタップする

### 時刻表の閲覧
トップページの上段に表示されているタイトルの右側にある▼マークをタップし、閲覧したい時刻表を選択する。

### 時刻表の編集
1.トップページのメニューから編集を選択する。

2.画面右上の▼マークをタップし、編集したい時刻表を選択する。

3.編集画面から編集したい箇所を選択し、編集する。

※時刻表を再度最初から作り直す場合は、運行時間を入力し、時刻表リセットボタンをタップしてください。この際入力してある時刻欄は消去されるので注意してください。

4.保存ボタンをタップする。

### 時刻表の消去
1.トップページのメニューから編集を選択し編集画面へ遷移する。

2.画面右上の▼マークをタップし、消去したい時刻表を選択する。

3.編集画面のメニューから消去を選択する。

4.表示されたダイアログの『OK』ボタンをタップする。

## 目指した課題解決
毎日使う路線の時刻表の管理
# 洗い出した要件
- 時刻表登録機能
- 時刻表表示機能
- 時刻表切り替え機能
- 時刻表編集機能
- 時刻表消去機能

# 画面
## トップページ
[![Image from Gyazo](https://i.gyazo.com/c3d00666c5e8526f49f7cbc9edca3cc1.jpg)](https://gyazo.com/c3d00666c5e8526f49f7cbc9edca3cc1)
## 新規登録画面
[![Image from Gyazo](https://i.gyazo.com/d791723271d776681b0cb23f16d26a5b.jpg)](https://gyazo.com/d791723271d776681b0cb23f16d26a5b)
## 新規 本 登録画面
[![Image from Gyazo](https://i.gyazo.com/57e0258f6a00fd1d2a96d60438f8453b.jpg)](https://gyazo.com/57e0258f6a00fd1d2a96d60438f8453b)
## ブック詳細画面
[![Image from Gyazo](https://i.gyazo.com/fadd515d5c7761857b7c48a7ca94f5c8.jpg)](https://gyazo.com/fadd515d5c7761857b7c48a7ca94f5c8)
## 読書前準備機能
[![Image from Gyazo](https://i.gyazo.com/453a62950d44714d6f5db698ae7c5f08.jpg)](https://gyazo.com/453a62950d44714d6f5db698ae7c5f08)


# re3-bookのテーブル設計
##  booksテーブル
| Column             | Type       | Options                         |
| ------------------ | ---------- | ------------------------------- |
|book_title          | string     | null: false                     |
|author              | string     | null: false                     |
|total_page          | integer    | null: false                     |
|read_page           | integer    |                                 |
|user                | references | null: false , foreign_key: true |

### Association
belongs_to :user
has_one :preparation
has_one :output



## Preparations
| Column             | Type        | Options                  |
| ------------------ | ----------- | ------------------------ |
| reason             | text        | null: false              |
| get_ability        | text        | null: false              |
| status             | text        | null: false              |
| prior_knowledge    | text        | null: false              |
| unknown            | text        | null: false              |
| book               | references  | null: false              |

### Association
belongs_to :book


## outputs テーブル
| Column             | Type        | Options                  |
| ------------------ | ----------- | -------------------------|
| text               | text        | null: false              |
| text               | text        | null: false              |
| book               | references  | null: false              |

### Association
belongs_to :book

# 使用言語・技術
- ktlin
- SQLite



