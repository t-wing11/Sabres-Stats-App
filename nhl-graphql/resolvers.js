const { fetchRoster } = require("./sampleData");
const { fetchPlayerInfo } = require("./sampleData");

const resolvers = {
  Query: {
    teams: () => [
      { id: "BUF", name: "Buffalo Sabres", abbreviation: "BUF" },
    ],
    teamRoster: async (_, { teamId, season }) => {
      // Fetch roster for the given team and season
      return await fetchRoster(teamId, season);
    },
    playerInfo: async (_, { playerId }) => {
      // Fetch player info for the given player
      return await fetchPlayerInfo(playerId);
    }
    
  },
};

module.exports = resolvers;
