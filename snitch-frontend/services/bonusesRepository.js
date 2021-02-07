import Repository from './repository';

const resource = "/bonus"
export default {
    get() {
        return Repository.get(`${resource}`);
    },
    getBonus(bonusId) {
        return Repository.get(`${resource}/${bonusId}`);
    },
    createBonus(payload) {
        return Repository.post(`${resource}`, payload);
    }
}