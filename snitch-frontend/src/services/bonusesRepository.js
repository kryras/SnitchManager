import Repository from './repository';

const resource = "/bonus"
export default {
    get() {
        return Repository.get(`${resource}`);
    },
    getById(bonusId) {
        return Repository.get(`${resource}/${bonusId}`);
    },
    create(payload) {
        return Repository.post(`${resource}`, payload);
    }
}