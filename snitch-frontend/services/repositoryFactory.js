import typesRepository from './typesRepository';
import snitchRepository from './snitchRepository';
import bonusesRepository from './bonusesRepository';
import userRepository from './userRepository';

const repositories = {
    snitch: snitchRepository,
    types: typesRepository,
    bonuses: bonusesRepository,
    user: userRepository
};

export const repositoryFactory = {
    get: name => repositories[name]
};