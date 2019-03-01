<#import "parts/common.ftl" as common>

<@common.page>

    <h3 class="text-center mt-2">Network List</h3>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">
                <form>
                    <div class="form-row align-items-center">
                        <div class="col-auto">
                            <#--<label for="exampleFormControlSelect1">Network Name</label>-->
                            <select class="form-control form-control-sm" id="exampleFormControlSelect1">
                                <#list networkNameList as networkName>
                                    <option>${networkName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                </form>
            </th>
            <th scope="col">Internet Address</th>
            <th scope="col">Physical Address</th>
            <th scope="col">Type</th>
        </tr>
        </thead>
        <tbody>
            <#list networkAddressModelList as networkAddressModel>

                <tr>
                <td style="text-align: center" colspan="4">${networkAddressModel.name}: &nbsp; &nbsp; &nbsp;ip4 = ${networkAddressModel.addressInet4}</td>
                </tr>

                <#list networkAddressModel.networkConnect.addressConnectModelList as addressConnectMode>
                    <tr>
                    <th scope="row"></th>
                    <td>${addressConnectMode.internetAddress}</td>
                    <td>${addressConnectMode.physicalAddress}</td>
                    <td>${addressConnectMode.type}</td>
                    </tr>
                </#list>

            </#list>

        </tbody>
    </table>

</@common.page>