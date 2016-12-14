# tt-translation
基于java 的 linux 终端翻译软件

## 配置
```
  sudo chmod +x set.sh
  source ~/.bashrc
```
## 文件配置
introduce 文件里面存放了程序说明

init.conf
```
from=auto #源语言
to=zh     #目的语言
appid=??? #百度给你的id
prikey=??? #百度给你的密钥
```
## 命令
```bash
dawndevil@DawnDevil  ~  tt
tt is a terminal translation software/ by dawndevil
tt [WORDS] [-OPTIONS]
for example:

tt hello -f en -t zh

-f source language   
-t destination language
-r reverse the language
---------------------------------------------------
language support
zh  : Chinese(简体中文)
en  : English(英语)
cht : Chinese traditional(繁体中文)
wym : Classical Chinses(文言文)
jp  : Jpanese(日语)
kor : Korean(韩语)
dan : Danish(丹麦)
ara : Arabic(阿拉伯)
est : Estonia(爱沙尼亚)
bul : Bulgaria(保加利亚)
pl  : Poland(波兰)
de  : German(德国)
ru  : Russian(俄罗斯)
fra : Franch(法语)
th  : Thai(台语)
nl  : Holland(荷兰)
cs  : Czesh(捷克)
rom : Romania(罗马尼亚)
pt  : Portuguese(葡萄牙)
swe : Swedish(瑞典)
slo : Slovenia(斯洛文尼亚)
spa : Spaish(西班牙语)
el  : Greek(希腊语)
hu  : Hungarian(匈牙利)

```

```
dawndevil@DawnDevil:~$ tt i wanna you
我想你

dawndevil@DawnDevil:~$ tt i wanna you -t jp
私はあなたが欲しいです

dawndevil@DawnDevil  ~  tt 你好 -f en -t zh -r
Hello

```
