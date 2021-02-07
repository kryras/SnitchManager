import Repository from './repository';

const resource = "/user"
export default {
    get() {
        return Repository.get(`${resource}`);
    },
    getRanking() {
        return Repository.get(`${resource}`+`/ranking`);
    },
    getById(userId) {
        return Repository.get(`${resource}/${userId}`);
    },
    create(payload) {
        return Repository.post(`${resource}`, payload);
    }
}