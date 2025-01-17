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
  }

  type SeasonTotal {
    season: String!
    team: String!
    goals: Int!
    assists: Int!
    points: Int!
    gamesPlayed: Int!
    leagueAbbrev: String!
    gameTypeId: Int!
  }

  type PlayerInfo {
    playerId: ID!
    headshot: String!
    firstName: String!
    lastName: String!
    sweaterNumber: Int
    position: String!
    shootsCatches: String!
    heightInInches: String!
    weightInPounds: Int!
    birthDate: String!
    birthCity: String!
    birthStateProvince: String!
    birthCountry : String!
    seasonTotals: [SeasonTotal!]!
  }

  type Query {
    teams: [Team!]!
    teamRoster(teamId: ID!, season: String!): [Player!]!
    playerInfo(playerId: ID!): PlayerInfo
    seasonTotal: SeasonTotal
  }
`;

module.exports = typeDefs;
