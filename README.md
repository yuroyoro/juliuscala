# Juliuscala

音声認識で遊ぶテスト。

オープンソースの音声認識エンジン Juilus(http://julius.sourceforge.jp/index.php)を使ってる。

## Juilusのインストール

PortAudioを入れる

  $ brew install --universal portaudio

Mac OSX Lionの場合は、最新のソースコードを以下からダウンロードし、make/make installしる

  http://sourceforge.jp/projects/julius/cvs/

  $ env CFLAGS='-arch i386' ./configure --enable-words-int --with-mictype=portaudio --disable-zlib
  $ make
  $ sudo make install

ディクテーションキットもダウンロードして展開しておけ。

  $ wget "http://sourceforge.jp/frs/redir.php?m=iij&f=%2Fjulius%2F51158%2Fdictation-kit-v4.1.tar.gz" -O dictation-kit-v4.1.tar.gz
  $ tar xzf dictation-kit-v4.1.tar.gz
  $ cd dictation-kit-v4.1


参考: Blog::About::Digital : 音声認識エンジンjuliusをLionで試してみた : http://about-digital.ldblog.jp/archives/458338.html

## 実行

以下のproertiesファイルをプロジェクトディレクトリ直下においてくれ。ConsumerKeyとか AccessTokenとかは各自で用意汁。

consumer.properties

  key=AAABBBBBBBBBBBBBBBBBB
  secret=fdafafewafdafdafdadbBDDDDDDDDDDDDDDDD

accesstoken.properties

  oauth_token=hogehgoehgoe
  oauth_token_secret=aaaaaaaaaaabfejefaeiafe

juilus.proerties

  julius_path=/usr/local/bin
  julius_conf=/Users/ozaki/src/julius4/dictation-kit-v4.1/fast.jconf

sbtでsbt runすると起動する
