<#import "parts/common.ftl" as common>

<@common.page>

    <h3 class="text-center mt-2">Network List</h3>


    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">
            <form method="get">
                <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                    <div class="btn-group" role="group">
                        <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle btn-sm"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${name}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        <#list networkNameList as networkName>
                            <a  class="dropdown-item" href="/?name=${networkName}">${networkName}</a>
                        </#list>
                        </div>
                    </div>
                </div>
            </form>
            </th>
            <th scope="col">Internet Address</th>
            <th scope="col">Physical Address</th>
            <th scope="col">Type</th>
            <th scope="col">Network</th>
        </tr>
        </thead>
        <tbody>
            <#list networkAddressModelList as networkAddressModel>

                <tr class="table-primary">
                <td style="text-align: center" colspan="5">${networkAddressModel.name}: &nbsp; &nbsp; &nbsp;ip4 = ${networkAddressModel.addressInet4}</td>
                </tr>

                <#list networkAddressModel.networkConnect.addressConnectModelList as addressConnectMode>
                    <tr>
                    <th scope="row"></th>
                    <td>${addressConnectMode.internetAddress}</td>
                    <td>${addressConnectMode.physicalAddress}</td>
                    <td>${addressConnectMode.type}</td>
                    <td>
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <form method="post" action="/?status=1&name=${name}">
                                <button type="submit" class="btn btn-secondary
                                ${(connect??)?string('disabled', '')}">Connect</button>
                            </form>
                            <form method="post" action="/?status=0&name=${name}">
                                <button type="submit" class="btn btn-secondary
                                ${(disconnect??)?string('disabled', '')}">Disconnect</button>
                            </form>
                        </div>
                    </td>
                    </tr>
                </#list>

            </#list>

        </tbody>
    </table>

</@common.page>