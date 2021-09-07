# バス時刻表ツクール

## 制作背景
私が学生の頃スクールバスを利用し通学していたが、時刻表を調べようとしてもスマートフォンの検索アプリの検索には引っかからず、毎回面倒臭いと思いながらも画像フォルダーの中にある時刻表の写真を探していた。そんな体験からスクールバスや地域のローカルバスを利用している人がスマートフォンですぐに時刻表を閲覧できるアプリを制作するに至りました。

## アプリケーションの概要
バスの時刻表を作成し、いつでも閲覧や編集ができるアプリです。
このアプリを使うことで、スクールバスや会社の通勤バスなどのいつも利用するバスの時刻表をアプリで確認できるようになります。

### 検索に引っかからないバスの時刻表をアプリで管理
学校のスクールバスや地域のローカルバスなど、経路案内の検索に引っかからないバスの時刻表をこのアプリ内に登録することで、いつでも時刻表を閲覧できるようにする。

### いつでも自分で時刻表を編集できる
時刻表が変更になったり、利用しなくなったバスがある場合は自分でいつでも時刻表を変更、消去ができる。

## Google playのURL
https://play.google.com/store/apps/details?id=com.websarva.wings.android.busschedulemaker

## 利用方法
### 時刻表の作成
1.トップページのメニューから新規時刻表を作成を選択する。<br>
2.タイトル、行き先、乗車場所、運行時間を入力し、時刻表入力欄作成ボタンをタップする。<br>
3.表示された時刻表にバスの出発時刻を入力し、保存ボタンをタップする

### 時刻表の閲覧
トップページの上段に表示されているタイトルの右側にある▼マークをタップし、閲覧したい時刻表を選択する。

### 時刻表の編集
1.トップページのメニューから編集を選択する。<br>
2.画面右上の▼マークをタップし、編集したい時刻表を選択する。<br>
3.編集画面から編集したい箇所を選択し、編集する。<br>
※時刻表を再度最初から作り直す場合は、運行時間を入力し、時刻表リセットボタンをタップしてください。この際入力してある時刻欄は消去されるので注意してください。<br>
4.保存ボタンをタップする。

### 時刻表の消去
1.トップページのメニューから編集を選択し編集画面へ遷移する。<br>
2.画面右上の▼マークをタップし、消去したい時刻表を選択する。<br>
3.編集画面のメニューから消去を選択する。<br>
4.表示されたダイアログの『OK』ボタンをタップする。

## 目指した課題解決
毎日使う路線の時刻表の管理するし、検索等に引っかからない時刻表もアプリで閲覧できるようにすること。
## 洗い出した要件
- 時刻表登録機能
- 時刻表表示機能
- 時刻表切り替え機能
- 時刻表編集機能
- 時刻表消去機能

## 画面
## トップページ
<img src="https://user-images.githubusercontent.com/82791666/132306187-2383465e-dc06-4b8b-8e0d-93d784fefe5d.jpg" width="300">  <img src="https://user-images.githubusercontent.com/82791666/132306212-16c10b1a-5152-4b52-95d5-7a4701d081dd.jpg" width="300">  <img src="https://user-images.githubusercontent.com/82791666/132306875-f8dfe8cb-ad7a-4085-aad7-1e0da5f0b839.jpg" width="300">

## 編集画面
<img src="https://user-images.githubusercontent.com/82791666/132304374-df5b93b6-fff5-40e6-9d72-620adea29f19.jpg" width="300">  <img src="https://user-images.githubusercontent.com/82791666/132304391-9149a568-e7a3-4b75-85fc-9405c0595a13.jpg" width="300">  <img src="https://user-images.githubusercontent.com/82791666/132304418-1ddd50b9-41d3-43ee-b80f-3941d8daacbf.jpg" width="300">

## データベース設計
## テーブル設計
### busschedulesテーブル
| Column             | Type       | Options                         |
| ------------------ | ---------- | ------------------------------- |
|_id                 | long       | INTEGER PRIMARY KEY             |
|title               | string     |                                 |
|destination         | string     |                                 |
|firstTrainTime      | long       |                                 |
|lastTrainTime       | long       |                                 |


## timetableテーブル
| Column             | Type        | Options                  |
| ------------------ | ----------- | ------------------------ |
| _id                | long        | NTEGER PRIMARY KEY       |
| o1                 | long        |                          |
| o2                 | long        |                          |
| o3                 | long        |                          |
| o4                 | long        |                          |
| o5                 | long        |                          |
| o6                 | long        |                          |
| o7                 | long        |                          |
| o8                 | long        |                          |
| o9                 | long        |                          |
| o10                | long        |                          |
| o11                | long        |                          |
| o12                | long        |                          |
| o13                | long        |                          |
| o14                | long        |                          |
| o15                | long        |                          |
| o16                | long        |                          |
| o17                | long        |                          |
| o18                | long        |                          |
| o19                | long        |                          |
| o20                | long        |                          |
| o21                | long        |                          |
| o22                | long        |                          |
| o23                | long        |                          |
| o24                | long        |                          |

# 使用言語・技術
- kotlin
- SQLite



