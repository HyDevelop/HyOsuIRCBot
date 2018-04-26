# HyOsuIRCBot
A Java IRC Bot for Osu!

### Credit:
- PircBotX for IRC Bot Library
    - Github: https://github.com/pircbotx/pircbotx
- Uso Bot for Beatmap Suggestion Algorithm 
    - Github: https://github.com/Renondedju/Uso_Bot_V2.0
- Osu!Track for Player Data Tracking & Analysis 
    - Website: https://ameobea.me/osutrack/
    - Github: https://github.com/Ameobea/osutrack_irc_v2



#### TODO List (in Chinese, obviously):

    ▷: 排队
    ▶: 开发中

    [ ]: 未完成
    [?]: 不确定
    [-]: 部分完成
    [D]: 需要测试
    [X]: 完成
    [#]: 取消

    TODO: 添加功能:
    ▷ [#] 注册个机器人账号 ( 被ppy拒绝 )
    ▶ [-] 指令系统
        ▷ [X] 框架
            ▷ [X] 基础框架
            ▷ [X] 检测权限 ( 需要权限组 )
        ▷ [-] 帮助指令
            ▷ [X] 帮助指令
            ▶ [ ] 帮助内容 ( 需要语言文件 )
        ▷ [X] 列出所有指令的指令
        ▶ [ ] 查询玩家信息的指令
    ▶ [-] 用户系统
        ▶ [?] 数据库
        ▶ [D] 权限组
            ▷ [X] 框架
            ▷ [X] 应用 ( 需要获取用户 )
            ▶ [ ] 存储移至数据库
        ▷ [X] 获取用户
        ▷ [#] 管理服务器 ( 同时连两个服务器, 一个是Osu, 另一个是私服用来管理 ) #无法实现
        ▷ [ ] 检测新用户
            ▷ [ ] 新用户提示设置语言
    ▶ [-] 语言文件
        ▷ [X] 读取语言文件
        ▷ [?] 优化语言文件
        ▷ [ ] 分用户设置语言
            ▷ [ ] 检测Osu客户端语言
        ▷ [-] 用来回复消息的类
            ▷ [ ] 放在用户下直接用语言文件码回复消息
    ▷ [?] 后台log转发...?

    TODO: 修复Bug:
    ▷ [X] 登陆/退出消息刷屏
