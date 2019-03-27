<#import "parts/common.ftl" as common>

<@common.page>

    <h3 class="text-center mt-2">Network List</h3>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">
            </th>
            <th scope="col">Name</th>
            <th scope="col">Internet Address</th>
            <th scope="col">Physical Address</th>
            <th scope="col">Network</th>
        </tr>
        </thead>
        <tbody>
            <#list addressConnectList as addressConnect>
                <tr>
                <th scope="row"></th>
                <td>addressConnect.name</td>
                <td>addressConnect.internetAddress</td>
                <td>addressConnect.physicalAddress</td>
                <td>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <form method="post" action="/?status=1">
                            <input type="hidden" name="ip" value="<#if addressConnect.internetAddress??>{addressConnect.internetAddress}</#if>
                            <button type="submit" class="btn btn-secondary
                            ${(connect??)?string('disabled', '')}">Connect</button>
                        </form>
                        <form method="post" action="/?status=0">
                            <input type="hidden" name="ip" value="<#if addressConnect.internetAddress??>{addressConnect.internetAddress}</#if>
                            <button type="submit" class="btn btn-secondary
                            ${(disconnect??)?string('disabled', '')}">Disconnect</button>
                        </form>
                    </div>
                </td>
                </tr>
            </#list>

        </tbody>
    </table>

</@common.page>