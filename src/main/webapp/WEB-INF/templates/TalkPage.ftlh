<#-- @ftlvariable name="users" type="ru.itmo.webmail.model.domain.User[]" -->
<#import "common.ftl" as c>

<@c.page>
<div class="form-box">
    <div class="header">Send Message</div>
    <div class="body">
        <form method="post" action="/talk">
            <input type="hidden" name="action" value="talk">
            <div class="field">
                <div class="name">
                    <label for="login">Send to</label>
                </div>
                <div class="value">
                    <input id="login" name="login" value="${login!}"/>
                </div>
            </div>
            <div class="field">
                <div class="name">
                    <label for="text">Message</label>
                </div>
                <div class="value">
                    <input id="text" type="text" name="message" value="${message!}"/>
                </div>
            </div>

            <div class="button-field">
                <input type="submit" value="Send">
            </div>
            <#if error??>
            <div class="error">
                ${error}
            </div>
            </#if>
        </form>
    </div>
</div>
<div class="datatable">
    <div class="caption">Messages</div>
    <table>
        <thead>
        <tr>
            <th>#</th>

            <th>Id Receiver</th>
            <th>Receiver Name</th>
            <th>Text</th>
        </tr>
        </thead>
        <tbody>
        <#list talks as talk>
        <tr>
            <td>
                ${talk_index + 1}
            </td>
            <td>
                ${talk.targetId}
            </td>
            <td>${talk.target}</td>
            <td>
                ${talk.text}
            </td>
        </tr>
        </#list>
    </table>
</div>
</@c.page>
