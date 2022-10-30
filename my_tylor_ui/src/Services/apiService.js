import axios from 'axios';
import { serverUrllocal, serverUrlprod } from '../Constants/ApiConstants';

export async function getApiService(url) {
    let response = await axios.get(url);
    return response; 
}

export async function  postApiService(url, body) {
    try {
        let currentUrl = null;
        if (window.location.href.includes("localhost")) {
            currentUrl = serverUrllocal;
        } else {
            currentUrl = serverUrlprod;
        }
        let response = await axios.post(`${currentUrl}${url}`, body);
        return response; 
    } catch(e) {
        return e['response']
    }

}