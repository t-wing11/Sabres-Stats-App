const { fetchRoster } = require("./sampleData");
const { fetchPlayerStats } = require("./sampleData");

const resolvers = {
  Query: {
    teams: () => [
      { id: "BUF", name: "Buffalo Sabres", abbreviation: "BUF" },
    ],
    teamRoster: async (_, { teamId, season }) => {
      // Fetch roster for the given team and season
      return await fetchRoster(teamId, season);
    }
    // playerStats: async (_, { playerId }) => {
    //   // Fetch stats for the given player and season
    //   return await fetchPlayerStats(playerId);
    // },
  },
};

module.exports = resolvers;
