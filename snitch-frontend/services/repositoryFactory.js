import typesRepository from './typesRepository';
import snitchRepository from './snitchRepository';
import bonusesRepository from './bonusesRepository';

const repositories = {
    snitch: snitchRepository,
    types: typesRepository,
    bonuses: bonusesRepository
};

export const repositoryFactory = {
    get: name => repositories[name]
};