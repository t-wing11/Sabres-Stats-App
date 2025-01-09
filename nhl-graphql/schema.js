const { gql } = require("apollo-server");

const typeDefs = gql`
  type Team {
    id: ID!
    name: String!
    abbreviation: String!
  }

  type Player {
    id: ID!
    headshot: String!
    firstName: String!
    lastName: String!
    sweaterNumber: Int     
    positionCode: String!
    shootsCatches: String!
    heightInInches: Int!
    weightInPounds: Int!
    heightInCentimeters: Int!
    weightInKilograms: Int!
    birthDate: String!
    birthCity: String!
    birthStateProvince: String
    birthCountry: String!
  }

  type Query {
    teams: [Team!]!
    teamRoster(teamId: ID!, season: String!): [Player!]!
  }
`;

module.exports = typeDefs;
