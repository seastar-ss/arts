tmplConfig(id,userImg,userName,userTitle,position,email,mobile,location,additionalInfo,useVendorInfo)::=<<
<if(userImg && userName && userTitle)>
\<div class="lingxi-signature-container">
    \<a id="lingxi-signature-block"
       style="display: block;width: 100%;position: relative;text-decoration: none;text-underline: none;"
       href="https://sirius-desktop-web.cowork.netease.com/jumpSig?id=<id>&show=">
        \<div style="position: absolute;top:16px;left: 16px;border-radius: 50%">
            \<img src="<userImg>" style="border-radius: 50%"
                 itemprop="&#45;&#45;替换头像&#45;&#45;" itemid="avatar" width="40px" height="40px"/>
        \</div>
        \<div id="lingxi-signature-content" itemid="id" itemprop="<id>"
             style="font-size: 14px;line-height: 16px;color: #7D8085;border-bottom: 1px solid rgba(38, 42, 51, 0.08);padding: 16px 16px 4px 4px;margin-left: 68px;word-break: break-all; ">
            \<div style="color: #262A33;border: none;margin-bottom:10px;font-size: 16px;text-underline: none;" itemprop="--替换name--"
                 itemid="name"><userName>
            \</div>
            \<div style="color: #262A33;border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换title--" itemid="title"><userTitle>\</div>
            <if(position)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换email--" itemid="position"><position>\</div><endif>
            <if(email)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换email--" itemid="email"><email>\</div><endif>
            <if(mobile)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换mobile--" itemid="mobile"><mobile>\</div><endif>
            <if(location)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换location--" itemid="location"><location>\</div><endif>
            <if(additionalInfo)>
            < additionalInfo :{ item |
                \<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换additionalInfo--"><item>\</div>
            }
            >
            <endif>
        \</div>
    \</a>
    <if(useVendorInfo)>
    \<div style="padding-left: 68px;color: #A8AAAD;font-size: 12px;margin-top: 12px">
        签名由
        \<a href="https://office.163.com/" style="color:#386EE7;display: inline;text-underline: none;">网易灵犀办公\</a>
        定制
    \</div>
    <endif>
\</div>
<elseif(!userImg && userName && userTitle)>
\<a id="lingxi-signature-v2-block"
   style="display: block;width: 100%;padding-right: 10px;text-decoration: none;text-underline: none;"
   href="https://sirius-desktop-web.cowork.netease.com/jumpSig?id=<id>&show=">
    \<div id="lingxi-signature-v2-content" itemid="id" itemprop="<id>"
         style="width: 100%;font-size: 14px;line-height: 16px;color: #7D8085;padding-bottom:4px; margin: 16px; ">
        \<div style="color: #262A33;border: none;margin-bottom:10px;font-size: 16px;text-underline: none;" itemprop="--替换name--"
             itemid="name"><userName>
        \</div>
        \<div style="color: #262A33;border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换title--" itemid="title"><userTitle>\</div>
        <if(position)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换email--" itemid="position"><position>\</div><endif>
        <if(email)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换email--" itemid="email"><email>\</div><endif>
        <if(mobile)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换mobile--" itemid="mobile"><mobile>\</div><endif>
        <if(location)>\<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换location--" itemid="location"><location>\</div><endif>
        <if(additionalInfo)>
        < additionalInfo :{ item |
            \<div style="border: none;margin-bottom:10px;text-underline: none;" itemprop="--替换additionalInfo--"><item>\</div>
        }
        >
        <endif>
    \</div>
\</a>
<elseif(userImg && userName && !userTitle)>
\<a id="lingxi-signature-v3-block"
   style="display: block;width: 100%;position: relative;text-decoration: none;text-underline: none;"
   href="https://sirius-desktop-web.cowork.netease.com/jumpSig?id=<id>&show=">
    \<div style="position: absolute;top:16px;left: 16px;border-radius: 50%">
            \<img src="<userImg>" style="border-radius: 50%" itemprop="&#45;&#45;替换头像&#45;&#45;" itemid="avatar" width="40px" height="40px"/>
    \</div>
    \<div id="lingxi-signature-v3-content" itemid="id" itemprop="<id>"
         style="width: 100%;font-size: 14px;line-height: 16px;color: #7D8085;padding-bottom:14px; margin-bottom:16px; ">
        \<div style="color: #262A33;border: none;padding: 16px 16px 4px 4px;margin-left: 68px;font-size: 16px;position: relative; top: 10px;"
             itemprop="--替换name--" itemid="name"><userName>
        \</div>
    \</div>
\</a>
<endif>
>>