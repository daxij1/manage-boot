let urlPrefix = window.location.origin
//本地调试改成该地址
//let urlPrefix = 'http://localhost:9999'
function get(uri) {
    return axios.get(urlPrefix + uri)
        .then(function (response) {
            response = response.data;
            if (response.code === 403) {
                jumpPage("/login.html")
            } else {
                return response;
            }
        }).catch(function (error) { // 请求失败处理
        console.log(error);
    });
}
function post(uri, param) {
    return axios.post(urlPrefix + uri, param)
        .then(function (response) {
            response = response.data;
            if (response.code === 403) {
                jumpPage("/login.html")
            } else {
                return response;
            }
        }).catch(function (error) { // 请求失败处理
            console.log(error);
        });
}
function jumpPage(uri) {
    window.location.href = urlPrefix + uri;
}