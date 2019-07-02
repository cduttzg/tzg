function Get(url){
    return fetch(url, {
        method : "GET",
        headers : new Headers({
            'Content-Type': 'application/json'
        })
    }).then(response => {
        return handlResponse(url, response);
    }).catch(error => {
        console.error(`Requset failed. Url=${url}. Message=${error}`);
        return {
            error : {
                messge : 'request failed'
            }
        };
    });
}
function Post(url, data){
    return fetch(url, {
        method : "POST",
        headers : new Headers({
            'Content-Type': 'application/json'
        }),
        body : JSON.stringify(data)
    }).then(response => {
        return handlResponse(url, response);
    }).catch(error => {
        console.error(`Requset failed. Url=${url}. Message=${error}`);
        return {
            error : {
                messge : 'request failed'
            }
        };
    });
}
function PutImage(url, formData){
    return fetch(url, {
        method : "PUT",
        body : formData
    }).then(response => {
        return handlResponse(url, response);
    }).catch(error => {
        console.error(`input image failed. Url=${url}. Message=${error}`);
        return {
            error : {
                messge : 'request failed'
            }
        };
    })
}
function handlResponse(url, response){
    if(response.status < 500){
        return response.json();
    }else{
        console.error(`Requset failed. Url=${url}. Message=${response.statusText}`);
        return {
            error : {
                message : 'request failed due to server error'
            }
        }
    }
}
export {
    Get, Post, PutImage
}